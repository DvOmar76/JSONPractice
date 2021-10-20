package com.example.jsonpractice

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetName.setOnClickListener {
            try {
                val input=edNumber.text.toString().toInt()
                if (input>13||input<1){
                    Toast.makeText(applicationContext, "please enter number between 1 an 13", Toast.LENGTH_SHORT).show()
                }else {
                    getNames()
                }
            }catch (e:Exception){
                Toast.makeText(applicationContext, "please enter number ", Toast.LENGTH_SHORT).show()

            }

        }

    }
    fun getNames() {
        funProgressDialog()
        if (apiInterface != null) {
            apiInterface.getUser()?.enqueue(object : Callback<Array<DataItem>?> {
                override fun onResponse(
                    call: Call<Array<DataItem>?>,
                    response: Response<Array<DataItem>?>
                ) {
                    progressDialog.dismiss()
                    val users=response.body()!!
                    var count=1
                    for (user in users) {
                        if (count==edNumber.text.toString().toInt()){
                            textView.text=user.name
                        }
                        count++
                    }

                }


                override fun onFailure(call: Call<Array<DataItem>?>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
    fun funProgressDialog() {
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
    }
}