package com.crazy.coder.ui

import com.crazy.coder.R
import android.R.menu
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.crazy.coder.data.repos.GitRepositoriesRepo
import com.crazy.coder.databinding.ActivityHomeBinding
import com.crazy.coder.domain.GetRepositoriesUseCase


class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(mBinding?.root)
        if (savedInstanceState == null) {
            initData()
        }
        initRV()
        observers()
    }

    private fun initData() {
        loader(true)
        viewModel.getRepositories()
    }

    private fun loader(isLoading: Boolean) {
        mBinding?.loadingPb?.isVisible = isLoading
    }

    private val viewModel by viewModels<GithubViewModel> {
        GitViewModelFactory(GetRepositoriesUseCase(GitRepositoriesRepo()))
    }

    private fun observers() {
        viewModel.repoList.observe(this) {
            loader(false)
            mAdapter.submitList(it)
        }
    }

    private lateinit var mAdapter: HomeAdapter
    private fun initRV() {
        mBinding?.repositoriesRv?.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            mAdapter = HomeAdapter(viewModel)
            itemAnimator = null
            adapter = mAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.crazy.coder.R.menu.home_menu, menu)

        val myActionMenuItem = menu!!.findItem(com.crazy.coder.R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Toast like print
//                UserFeedback.show("SearchOnQueryTextSubmit: $query")
                if (!searchView.isIconified()) {
                    searchView.setIconified(true)
                }
                myActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);

                return false
            }
        })

        return true


//        menuInflater.inflate(R.menu.home_menu, menu)
//
//        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView: SearchView = searchItem?.actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if(item.itemId == R.id.action_search){
//
//        } else
//        super.onOptionsItemSelected(item)
//    }
}