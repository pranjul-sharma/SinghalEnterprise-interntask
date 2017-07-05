package in.singhalenterprises.singhalenterprise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean navItemClikced;
    private Runnable mPendingRunnable;
    View headerView;
    NavigationView navigationView;
    FragmentManager fm;
    FragmentTransaction ft;
    MenuItem prevItem=null;
    DrawerLayout drawer;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (navItemClikced)
                    mPendingRunnable.run();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView=navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        fm=getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment=fm.findFragmentByTag("visible_fragment");
                Menu menuNav=navigationView.getMenu();
                MenuItem item;
                if(fragment instanceof HomeFragment){
                    getSupportActionBar().setTitle(R.string.app_name);
                    item=menuNav.findItem(R.id.drawer_home);
                    prevItem.setChecked(false);
                    item.setChecked(true);
                    prevItem=item;
                }
            }
        });

        ft=fm.beginTransaction();
        ft.replace(R.id.frame_main,new HomeFragment(),"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        prevItem=navigationView.getMenu().getItem(0);
        navigationView.getMenu().getItem(0).setChecked(true);

        mPendingRunnable=new Runnable() {
            @Override
            public void run() {
                ft=fm.beginTransaction();
                if(id==R.id.drawer_home){
                    ft.replace(R.id.frame_main,new HomeFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.app_name);
                }
                else if(id==R.id.drawer_products){
                    ft.replace(R.id.frame_main,new ProductFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.products);
                }else if (id == R.id.drawer_ecatelogue) {
                    ft.replace(R.id.frame_main,new ECatalogueFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.ecatalogue);
                }
                else if (id == R.id.drawer_about_us) {
                    ft.replace(R.id.frame_main,new AboutUsFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.about_us);
                } else if (id == R.id.drawer_blog) {
                    ft.replace(R.id.frame_main,new BlogFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.blog);

                } else if (id == R.id.drawer_contact_us) {
                    ft.replace(R.id.frame_main,new ContactFragment(),"visible_fragment");
                    getSupportActionBar().setTitle(R.string.contact_us);
                }
                if(id!=R.id.drawer_home && fm.getBackStackEntryCount()!=2)
                    ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                navItemClikced=false;
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        navItemClikced=true;
        id = item.getItemId();
        item.setCheckable(true);
        prevItem.setChecked(false);
        item.setChecked(true);
        prevItem=item;

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(prevItem!=null){
            prevItem.setChecked(false);
            prevItem=navigationView.getMenu().findItem(R.id.drawer_home);
            prevItem.setChecked(true);
        }
    }
}
