package com.example.cleanarcitecture.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarcitecture.R
import com.example.cleanarcitecture.domain.entity.Person
import com.example.cleanarcitecture.presentation.adapter.ItemClickListener
import com.example.cleanarcitecture.presentation.adapter.PersonAdapter
import com.example.cleanarcitecture.presentation.viewModel.AddItemState
import com.example.cleanarcitecture.presentation.viewModel.MainViewModel

class MainFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var nameInput: EditText
    private lateinit var rateInput: EditText
    private lateinit var addPersonBtn: Button
    private lateinit var personsList: RecyclerView
    private lateinit var stateText: TextView
    private var adapter = PersonAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        nameInput.doAfterTextChanged {
            viewModel.name = it.toString()
        }

        rateInput.doAfterTextChanged {
            viewModel.rate = it.toString().toInt()
        }

        addPersonBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || rateInput.text.isEmpty()){
                val toast = Toast.makeText(requireContext(), "input field are empty",Toast.LENGTH_SHORT)
                toast.show()
            } else viewModel.registerPerson()
        }

        viewModel.getPersons().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        viewModel.addItemState.observe(viewLifecycleOwner, Observer {
            when (it) {
                AddItemState.Free -> addPersonBtn.isEnabled = true
                else -> addPersonBtn.isEnabled = false
            }
            stateText.text = getString(
                when (it) {
                    AddItemState.Free -> R.string.free_state
                    AddItemState.Loading -> R.string.loading_state
                    AddItemState.Result -> R.string.rezult_state
                }
            )
            when (it){
                AddItemState.Free -> addPersonBtn.isEnabled = true
                AddItemState.Loading -> addPersonBtn.isEnabled = false
                AddItemState.Result -> addPersonBtn.isEnabled = false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameInput = view.findViewById(R.id.name_input)
        rateInput = view.findViewById(R.id.rate_input)
        addPersonBtn = view.findViewById(R.id.add_person_btn)
        personsList = view.findViewById(R.id.persons_list)
        stateText = view.findViewById(R.id.state_text)

        personsList.layoutManager = LinearLayoutManager(requireContext())
        personsList.adapter = adapter
        adapter.setListener(this)
    }
    override fun onItemClick(person: Person) {
        viewModel.onOperationSelected(person)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.setListener(null)
    }


}