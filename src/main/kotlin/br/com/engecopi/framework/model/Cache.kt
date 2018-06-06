package br.com.engecopi.framework.model

import org.jetbrains.exposed.dao.IntIdTable
import kotlin.reflect.KProperty

const val duration = 30000

class ExpireValue<T>(private val _value: T?) {
  val expire: Long = System.currentTimeMillis() + duration
  val value: T?
    get() = if (expire > System.currentTimeMillis())
      _value
    else null
}

val mapCache = mutableMapOf<String, ExpireValue<*>>()

class DelegadeCacheValue<T>(private val initializer: () -> T?) {
  private var millisecond = System.currentTimeMillis()
  
  operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
    val classe = thisRef?.javaClass?.simpleName
    val key = if (thisRef is IntIdTable) {
      val id = thisRef.id
      "$classe:$id:${property.name}"
    } else {
      "$classe:${property.name}"
    }
    
    
    return synchronized(mapCache) {
      val expireValue = mapCache.getOrPut(key) {
        ExpireValue(initializer())
      }
      
      
      if (expireValue.expire < System.currentTimeMillis())
        mapCache[key] = ExpireValue(initializer())
      
      mapCache[key]?.value as T
    }
  }
}

class DelegadeCacheList<T>(private val initializer: () -> List<T>) {
  private var millisecond = System.currentTimeMillis()
  
  operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
    val classe = thisRef?.javaClass?.simpleName
    val key = if (thisRef is IntIdTable) {
      val id = thisRef.id
      "$classe:$id:${property.name}"
    } else {
      "$classe:${property.name}"
    }
    
    return synchronized(mapCache) {
      val expireValue = mapCache.getOrPut(key) {
        ExpireValue(initializer())
      }
      
      if (expireValue.expire < System.currentTimeMillis()) {
        val expireValue = ExpireValue(initializer())
        mapCache[key] = expireValue
        expireValue.value.orEmpty()
      } else
        (expireValue as? List<T>).orEmpty()
    }
  }
}

fun <T> cacheValue(initializer: () -> T?) = DelegadeCacheValue(initializer)

fun <T> cacheList(initializer: () -> List<T>) = DelegadeCacheList(initializer)