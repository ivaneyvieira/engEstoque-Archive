[com.github.vok.karibudsl](index.md) / [passwordField](.)

# passwordField

`fun <ERROR CLASS>.passwordField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`

Creates a [PasswordField](#).

*WARNING:* When Binding to a field, do not forget to call [Binder.BindingBuilder.trimmingConverter](#) to perform auto-trimming:
passwords generally do not have whitespaces. Pasting a password to a field in a mobile phone will also add a trailing whitespace, which
will cause the password to not to match, which is a source of great confusion.

