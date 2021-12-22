package com.example.dubizzletest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Base class to add common functionality.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }
}
