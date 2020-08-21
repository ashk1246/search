package com.white.whiterabbit.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.white.whiterabbit.AppDatabase
import com.white.whiterabbit.CompanyDao
import com.white.whiterabbit.R
import com.white.whiterabbit.model.CompanyResponseModel
import com.white.whiterabbit.di.CompanyInjection
import com.white.whiterabbit.model.db.CompanyEntity
import com.white.whiterabbit.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_error.*

class CompanyActivity :AppCompatActivity(){
    private lateinit var viewModel: CompanyViewModel

    private var db: AppDatabase? = null
    private var genderDao: CompanyDao? = null

    private var companyEnt: LiveData<List<CompanyEntity>>? = null
    private lateinit var adapter: SearchAdapter
    lateinit var input: EditText

    companion object {
        const val TAG= "CONSOLE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        db = AppDatabase.getAppDataBase(context = this)
        genderDao = db?.companyDao()

        setupViewModel()
        viewModel.loadSearch()
        input = findViewById(R.id.etInput)
        input.addTextChangedListener(textWatcher)

    }

    fun ser(s: String) {
        companyEnt = genderDao!!.getSearch(s,s)
        companyEnt!!.observe(this, Observer { str ->

            str?.let {
                if(it?.size!! >0){
                    adapter= SearchAdapter(it?: emptyList())
                    recyclerView.layoutManager= LinearLayoutManager(this)
                    recyclerView.adapter= adapter
                }
            }
        })

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.length >=3) {
                ser(s.toString())
            }
        }
    }


    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, CompanyInjection.provideViewModelFactory()).get(
            CompanyViewModel::class.java)


        viewModel.searchs.observe(this,companyResponse)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    private val companyResponse= Observer<List<CompanyResponseModel>> {
        if(it?.size!! >0){
            Thread {
            for(i in it?.indices){
                var companyEntity = CompanyEntity(id = it?.get(i)?.id!!,name = it?.get(i)?.name.toString(),email = it?.get(i)?.email.toString(),phone = it?.get(i)?.phone.toString(),profile_image = it?.get(i)?.profile_image.toString(),username = it?.get(i)?.username.toString(),website = it?.get(i)?.website.toString(),bs = it?.get(i)?.company?.bs.toString(),catchPhrase = it.get(i)?.company?.catchPhrase.toString(),city = it?.get(i)?.address?.city.toString(),companyName = it?.get(i)?.company?.name.toString(),lat = it.get(i)?.address?.geo?.lat.toString(),lng = it.get(i)?.address?.geo?.lng.toString(),street = it?.get(i)?.address?.street.toString(),suite = it.get(i)?.address?.suite.toString(),zipcode = it.get(i)?.address?.zipcode.toString())
                genderDao!!.insertCmpy(companyEntity)
            }
            }.start()

        }

        layoutError.visibility= View.GONE
        layoutEmpty.visibility= View.GONE

    }



    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        Toast.makeText(applicationContext,"No search found",Toast.LENGTH_SHORT).show()
//      layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }


    override fun onResume() {
        super.onResume()

    }

}