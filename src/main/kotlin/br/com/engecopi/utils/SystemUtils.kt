package br.com.engecopi.utils


import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URISyntaxException
import java.nio.charset.Charset
import javax.imageio.ImageIO
import java.nio.file.Paths
import java.nio.file.Files



object SystemUtils {
  private val enviroment = System.getenv()
  fun variable(`var`: String, def: String): String {
    val envResult = SystemUtils.enviroment[`var`]
    return if (envResult == null || envResult.trim { it <= ' ' } == "") {
      def
    } else envResult
  }
  
  @Throws(IOException::class)
  private fun toBufferedImage(imagem: ByteArray?): BufferedImage? {
    if (imagem == null) return null
    val `in` = ByteArrayInputStream(imagem)
    return ImageIO.read(`in`)
  }
  
  @Throws(IOException::class)
  private fun toByteArray(image: BufferedImage): ByteArray? {
    val baos = ByteArrayOutputStream()
    ImageIO.write(image, "jpg", baos)
    baos.flush()
    val imageInByte = baos.toByteArray()
    baos.close()
    return imageInByte
  }
  
 
  fun getResourceAsStream(name: String?): InputStream? {
    var nameRet = name
    nameRet = resolveName(nameRet)
    val cl = SystemUtils::class.java.classLoader ?: return ClassLoader.getSystemResourceAsStream(nameRet)
    return cl.getResourceAsStream(nameRet)
  }
  
  private fun resolveName(name: String?): String? {
    var nameRet = name
    if (nameRet == null) {
      return nameRet
    }
    if (!nameRet.startsWith("/")) {
      var c: Class<*> = SystemUtils::class.java
      while (c.isArray) {
        c = c.componentType
      }
      val baseName = c.name
      val index = baseName.lastIndexOf('.')
      if (index != -1) {
        nameRet = baseName.substring(0, index).replace('.', '/') + "/" + nameRet
      }
    } else {
      nameRet = nameRet.substring(1)
    }
    return nameRet
  }
  
  fun readFile(file: String): String? {
    return readFile(file, Charset.defaultCharset())
  }
  
  @Throws(IOException::class)
  fun readFile(path: String, encoding: Charset): String {
    val encoded = Files.readAllBytes(Paths.get(path))
    return String(encoded, encoding)
  }
}
