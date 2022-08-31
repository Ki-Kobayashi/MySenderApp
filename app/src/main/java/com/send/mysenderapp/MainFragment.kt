package com.send.mysenderapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.send.mysenderapp.databinding.FragmentMainBinding

/**
 * Created by K.Kobayashi on 2022/08/21.
 */
class MainFragment: Fragment(R.layout.fragment_main) {
    companion object {
        const val TAG = "MainFragment"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = FragmentMainBinding.bind(view)
        initViews(view)
    }

    private fun initViews(v: View) {
        // ListViewセット
        setListView()



        binding.btnStartService.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }

    private fun setListView() {
        val data = mutableListOf("天才", "大天才", "秀才", "博士クラス", "天才", "大天才", "秀才", "博士クラス","天才", "大天才", "秀才", "博士クラス","天才", "大天才", "秀才", "博士クラス", "神級の大天才")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            data
        )
        binding.listItemName.adapter = adapter

        binding.listItemName.setOnItemClickListener { av, view, position, id ->
            Toast.makeText(
                requireContext(),
                "選択したのは、${(view as TextView).text}です",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.listItemName.setOnItemLongClickListener { av, view, position, ld ->
            adapter.remove((view as TextView).text as String)
            // 以下をTrueにすることで、長押しだけの検知になる
            // false：LongClickの後にOnItemClickListenerも呼ばれてしまう
            // OnItemLongClickListenerの後は、基本的にOnItemClickListenerも呼ばれる
            true
        }
    }
}