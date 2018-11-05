# TextRef

## What?

TextRef is an abstraction over Android strings. It wraps a String or a string resource ID and with the help of a `Context`
a final string can be resolved. Format args are supported too!

## How?

**Create**
```kotlin
TextRef("My string")
TextRef(R.string.my_string)
TextRef("The numbers are %d and %d", 5, 7)
TextRef(R.string.the_numbers_are, 5, 7)
```

**Resolve**
```kotlin
val text: String = textRef.resolve(context)
```

## Why?
Consider this situation: You have an MVP application and want to present some text.
The problem is that this text is sometimes a `String` and sometimes a resource ID:

```kotlin
val userName = if (user != null) {
    TextRef(user.name)
} else {
    TextRef(R.string.guest)
}
view.renderUserName(userName)
```

Using TextRef, you're now "string agnostic" all the way to the view. In addition to a cleaner more consistent API
this also simplifies testing (no need to mock `Context`) and delays expensive string formatting until the very end.

## Requirements

* AndroidX enabled
* API Level >= 14

## Download

TODO
