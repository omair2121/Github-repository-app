package com.crazy.coder.ui

import com.crazy.coder.R
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.crazy.coder.data.repos.GitRepositoriesRepo
import com.crazy.coder.databinding.ActivityHomeBinding
import com.crazy.coder.domain.GetRepositoriesUseCase
import com.crazy.coder.libs.Utils.isConnected
import android.app.SearchManager
import android.content.Context
import android.view.MenuItem


class HomeActivity : AppCompatActivity() {

    // using data binding
    private var mBinding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(mBinding?.root)
        init(savedInstanceState == null)
        initRV()
        observers()
        listener()
    }

    // checking if device is connected to internet and first instance else showing related text
    private fun init(isSavedInstanceStateNull: Boolean) {
        if (isSavedInstanceStateNull && isConnected(this))
            initData()
         else
            stateText(0)
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
            stateText(it?.size ?: 0)
            mAdapter.submitList(it)
        }
    }

    private fun listener() {
        mBinding?.stateTv?.setOnClickListener {
            if(viewModel.isOriginalListAvailable()) {
                viewModel.reset()
                mSearchMenu?.collapseActionView()
            } else
                init(true)

            mBinding?.stateTv?.isVisible = false
        }
    }

    private fun stateText(size: Int) {
        mBinding?.stateTv?.isVisible = size == 0
        loader(false)
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

    private var mSearchMenu: MenuItem? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        mSearchMenu = menu?.findItem(R.id.action_search)
        val searchView = mSearchMenu?.actionView as? SearchView
        searchView?.maxWidth = Integer.MAX_VALUE

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                s.ifEmpty { viewModel.reset() }
                return false
            }
        })
        return true
    }
}