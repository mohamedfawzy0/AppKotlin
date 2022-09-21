package com.appkotlin.activities

import android.content.Intent
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
import com.appkotlin.service.MyService
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), UserAdapter.OnListItemClick {
    lateinit var binding: ActivityHomeBinding
    var userList: List<User> = emptyList()
    var userName: String? = null
    private val adapter: UserAdapter by lazy { UserAdapter(userList) }
    private val viewModel: HomeActivityMVVM by viewModel()
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
        val intent=Intent(this,MyService::class.java)
        startForegroundService(intent)
//        viewModel = ViewModelProvider(this).get(HomeActivityMVVM::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        getAllUsers()


        binding.btnAdd.setOnClickListener {

            val msg = binding.etAdd.text.toString()

            viewModel.addUserAPI(User(
                9,
                userName.toString(),
                msg,
                R.drawable.ic__user
            ))
//            viewModel.addUser(
//                User(
//                    0,
//                    userName.toString(),
//                    msg,
//                    R.drawable.ic__user
//                )
//            )

            getAllUsers()
            binding.etAdd.setText("")
        }
        Log.e("user_image",R.drawable.ic__user.toString())

        adapter.onListItemClick = this
        viewModel.usersAPILiveData.observe(this,
            Observer {
                if (it !=null) {
                    adapter.updateList(it)
                    binding.progressBar.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                }
            })

        viewModel.addUserAPILiveData.observe(this, Observer {
            if (it !=null){
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"The User ${it.name} is added successfully",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Connection Failed",Toast.LENGTH_LONG).show()

            }
        })

    }

    fun getAllUsers() {
//        viewModel.getUsers()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUsersAPI()

    }


    override fun onItemClick(user: User) {
//        viewModel.deleteUser(user)
        viewModel.deleteAPIUser(user.id)

        Toast.makeText(this, "The user ${user.name} is deleted successfully", Toast.LENGTH_LONG).show()
        Log.e("dataaa", user.name + " + " + user.message)

        getAllUsers()
    }


}