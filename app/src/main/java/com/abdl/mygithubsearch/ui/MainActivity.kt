package com.abdl.mygithubsearch.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdl.mygithubsearch.R
import com.abdl.mygithubsearch.adapter.ListUserAdapter
import com.abdl.mygithubsearch.data.remote.Repository
import com.abdl.mygithubsearch.data.remote.model.ItemsItem
import com.abdl.mygithubsearch.databinding.ActivityMainBinding
import com.abdl.mygithubsearch.viewmodel.MainViewModel
import com.abdl.mygithubsearch.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val listUserAdapter = ListUserAdapter()

        activityMainBinding.rvListUser.apply {
            adapter = listUserAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.myListUser.observe(this, Observer { response ->
            if (response != null) {
                showLoading(false)
            } else {
                Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_LONG).show()
            }

            if (response.isSuccessful) {
                response.body()?.items?.forEach {
                    Log.d("MainActivity", it?.login.toString())
                    Log.d("MainActivity", it?.id.toString())
                    Log.d("MainActivity", it?.avatarUrl.toString())
                }
                listUserAdapter.setData(response.body()?.items as ArrayList<ItemsItem>)

            } else {
                Log.d("MainActivity", "Gagal")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.getListUser(query)

                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activityMainBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityMainBinding.progressBar.visibility = View.GONE
        }
    }
}