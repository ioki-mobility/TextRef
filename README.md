# TextRef

[![Travis](https://travis-ci.org/ioki-mobility/TextRef.svg?branch=master)](https://travis-ci.org/ioki-mobility/TextRef)
[![Jitpack](https://jitpack.io/v/ioki-mobility/TextRef.svg)](https://jitpack.io/#ioki-mobility/TextRef)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ioki-mobility/TextRef/blob/master/LICENSE.md)

## What?

TextRef is an abstraction over Android strings. It's a wrapper around a String or string resource ID.
With the help of a `Context` a final string can be resolved. Format args are supported too!

## How?

**Create**
```kotlin
TextRef("My string")
TextRef(R.string.my_string)
TextRef("The arguments are %d and %s", 5, "foo")
TextRef(R.string.the_arguments_are, 5, "foo")
```

**Resolve**
```kotlin
val text: String = textRef.resolve(context)
```

## Why?

* **String agnostic APIs:** Make functions return TextRefs to allow for both strings and string resource IDs
* **Less dependent on Context:** No need to resolve string resources close to business logic such as view models
* **Lazy formatting:** Pass format args and let TextRef do the formatting as late as possible.
* **Simplified testing:** No need to mock `Context.getString`

## Example

Here's a simple MVP use case:

```kotlin
// View
fun renderUserName(text: TextRef) {
    userNameTextView.text = text.resolve(context)
}

// Presenter
val userName = if (user != null) {
    TextRef(user.name)
} else {
    TextRef(R.string.guest)
}
view.renderUserName(userName)
```

## Requirements

* API Level >= 14

## Download

TextRef is hosted on JitPack. Here's how you include it in your gradle project:

**Step 1.** Add the JitPack repository to your build file:

```groovy
allprojects {
    repositories {
        // Other repositories
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency:

```groovy
dependencies {
    implementation 'com.github.ioki-mobility:TextRef:<latest-version>'
}
```

## Releasing

**Step 1.** Make sure you are on the master branch.

**Step 2.** Add the changes to the top of CHANGELOG.md

**Step 3.** Commit and push

**Step 4.** Run the gradle release task:

```
./gradlew release -Prelease.forceVersion=x.y.z
```
