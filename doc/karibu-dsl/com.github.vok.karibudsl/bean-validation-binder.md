[com.github.vok.karibudsl](index.md) / [beanValidationBinder](.)

# beanValidationBinder

`inline fun <reified T : Any> beanValidationBinder(): <ERROR CLASS><T>`

Allows you to create [BeanValidationBinder](#) like this: `beanValidationBinder<Person>()` instead of `BeanValidationBinder(Person::class.java)`

