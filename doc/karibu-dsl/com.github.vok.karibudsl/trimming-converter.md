[com.github.vok.karibudsl](index.md) / [trimmingConverter](.)

# trimmingConverter

`fun <BEAN> <ERROR CLASS><BEAN, String?>.trimmingConverter(): <ERROR CLASS><BEAN, String?>`

Trims the user input string before storing it into the underlying property data source. Vital for mobile-oriented apps:
Android keyboard often adds whitespace to the end of the text when auto-completion occurs. Imagine storing a username ending with a space upon registration:
such person can no longer log in from his PC unless he explicitely types in the space.

