package com.example.dinamicfeature.presentation.config

import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfigViewModel : BaseViewModel() {

  val newList = listOf(ConfigData("Ajuste de busca","1"),
    ConfigData("Visualização","2") ,ConfigData("Editar perfil","3") ,ConfigData("Minha assinatura","4") ,
    ConfigData("Validar perfil","5") ,ConfigData("Notificações","6") ,ConfigData("Flash notes","7") ,
    ConfigData("Boots","8") ,ConfigData("Política de privacidade","9") ,ConfigData("Empresa","10") ,
    ConfigData("Contas conectadas","11") ,ConfigData("Ajuda","12") ,ConfigData("Informações e termos","13") ,
    ConfigData("Pausar","14") ,ConfigData("conta","15") ,ConfigData("Modo noturno","16") ,ConfigData("Excluir conta","17") ,
    )


  private val _listFlow = MutableStateFlow(newList)
  val listFlow: StateFlow<List<ConfigData>> = _listFlow

}