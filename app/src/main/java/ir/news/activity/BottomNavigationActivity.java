package ir.news.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.news.R;
import ir.news.fragment.BookmarkFragment;
import ir.news.fragment.HomeFragment;


public class BottomNavigationActivity extends AppCompatActivity
{

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bottom_navigation );
        findview();

        Fragment selectedFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, selectedFragment ).commit();

        bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override public boolean onNavigationItemSelected( @NonNull MenuItem item )
            {

                Fragment selectedFragment = null;

                switch (item.getItemId())
                {
                    case R.id.homeFragment:
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.bookmarkFragment:

                        selectedFragment = new BookmarkFragment();
                        break;

                    default:

                        selectedFragment = new HomeFragment();

                        break;
                }
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, selectedFragment ).commit();
                return true;
            }
        } );

    }


    private void findview()
    {
        bottomNav = findViewById( R.id.bottom_navigation );
    }
}
