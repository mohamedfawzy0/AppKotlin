package com.appkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.appkotlin.R
import com.appkotlin.adapters.UserAdapter
import com.appkotlin.databinding.ActivityHomeBinding
import com.appkotlin.models.entity.User
import com.appkotlin.models.local.LocalRepositoryImp
import com.appkotlin.models.local.UserDatabase
import com.appkotlin.mvvm.HomeActivityMVVM
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity(), UserAdapter.OnListItemClick {
    lateinit var binding: ActivityHomeBinding
    var userList: List<User> = emptyList()
    var userName: String? = null
    var adapter: UserAdapter = UserAdapter(userList)
    lateinit var viewModel: HomeActivityMVVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        getDataFromIntent()
        initView()

    }

    private fun getDataFromIntent() {
        if (intent != null) {
            userName = intent.extras!!.get("name").toString()
            Toast.makeText(this, "UserName :" + userName.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(HomeActivityMVVM::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        getAllUsers()


        binding.btnAdd.setOnClickListener {

            val msg = binding.etAdd.text.toString()

            viewModel.addUser(
                User(
                    0,
                    userName.toString(),
                    msg,
                    R.drawable.ic__user
                )
            )

            getAllUsers()
            binding.etAdd.setText("")
        }

        adapter.onListItemClick = this
        viewModel.usersLiveData.observe(this,
            Observer {
                if (!it.isNullOrEmpty()) {
                    adapter.updateList(it)
                    binding.progressBar.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                }else{
                    binding.tvNoData.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE


                }
            })

    }

    fun getAllUsers() {
        viewModel.getUsers()
        binding.progressBar.visibility = View.VISIBLE

    }


    override fun onItemClick(user: User) {
        viewModel.deleteUser(user)

        Toast.makeText(this, "The user is deleted successfully", Toast.LENGTH_LONG).show()
        Log.e("dataaa", user.userName + " + " + user.data)

        getAllUsers()
    }


}