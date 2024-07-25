package com.example.tmdb.presentation.ui.fragment.profile

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentContactBinding
import com.example.tmdb.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding, MainViewModel>() {
    override fun layoutResId() = R.layout.fragment_contact
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
    }
}

interface Animal {
    fun speak(): String
}

class Dog : Animal {
    override fun speak() = "Woof"
}

class Cat : Animal {
    override fun speak() = "Meow"
}

class AnimalFactory {
    fun createAnimal(type: String): Animal {
        return when (type) {
            "Dog" -> Dog()
            "Cat" -> Cat()
            else -> throw IllegalArgumentException("Unknown animal type")
        }
    }
}

fun main() {
    val factory = AnimalFactory()
    val animal = factory.createAnimal("Dog")
    println(animal.speak())
}