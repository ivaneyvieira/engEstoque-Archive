package br.com.engecopi.framework.model


import java.io.IOException

object MainQueryBeanGenerator {
  
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    
    val config = GeneratorConfig()
    config.lang = "kt"
    config.classesDirectory = "./out/production/classes/"
    //config.classesDirectory = "./build/classes/kotlin/main/"
    config.destDirectory = "./src/main/kotlin"
    config.destResourceDirectory = "./src/main/resources"
  
    config.entityBeanPackage = "br.com.engecopi.estoque.model"
    config.destPackage = "br.com.engecopi.estoque.model.query"
  
    config.isOverwriteExistingFinders = true
    
    val generator = Generator(config)
    generator.generateQueryBeans()
    
    // Additionally generate 'finder's
    generator.generateFinders()
    generator.modifyEntityBeansAddFinderField()
  }
}