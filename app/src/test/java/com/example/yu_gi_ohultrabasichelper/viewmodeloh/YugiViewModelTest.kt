package com.example.yu_gi_ohultrabasichelper.viewmodeloh

import android.app.Application
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class YugiViewModelTest{

    @Mock
    private lateinit var application:Application
    @Mock
    private lateinit var viewModel: YugiViewModel
    private var set:String = "Maximum Crisis"
    private var card:String = "Odd-Eyes Pendulum Dragon"

    @Before
    fun init(){
        application = Mockito.mock(Application::class.java)
        viewModel = Mockito.mock(YugiViewModel::class.java)
    }

    @Test
    fun setCardListTest(){
        viewModel.setCardsListToViewModel(set)
        verify(viewModel).setCardsListToViewModel(set)
    }

}