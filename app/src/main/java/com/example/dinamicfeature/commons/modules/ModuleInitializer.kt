package com.example.dinamicfeature.commons.modules

import org.koin.core.module.Module

object ModuleInitializer {
  val modules = mutableListOf<Module>()

  fun add(vararg modules: Module) {
    ModuleInitializer.modules.addAll(modules)
  }

  fun add(modules: List<Module>) {
    ModuleInitializer.modules.addAll(modules)
  }
}
