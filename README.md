# TextRef

[![CI](https://github.com/ioki-mobility/TextRef/actions/workflows/test-lib.yml/badge.svg)](https://github.com/ioki-mobility/TextRef/actions/workflows/test-lib.yml)
[![Jitpack](https://jitpack.io/v/ioki-mobility/TextRef.svg)](https://jitpack.io/#ioki-mobility/TextRef)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ioki-mobility/TextRef/blob/master/LICENSE.md)

## What?

TextRef is an abstraction over Android text. It wraps a `String` or a string/plurals resource ID.
With the help of an Android `Context` a final `String` can be resolved. Format args are supported too!

## How?

**Create**
```kotlin
TextRef.string("My string")
TextRef.stringRes(R.string.my_string)
TextRef.pluralsRes(R.plurals.number_of_items, 3)
TextRef.string("The arguments are %d and %s", 5, "foo")
```

**Resolve**
```kotlin
val text: String = textRef.resolve(context)
```

## Why?

* **String agnostic APIs:** Make functions return TextRefs to allow for any type of text representation
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
    TextRef.string(user.name)
} else {
    TextRef.stringRes(R.string.guest)
}
view.renderUserName(userName)
```

## Requirements

* API Level >= 14

## Download

TextRef is hosted on JitPack. Here's how you include it in your gradle project:

**Step 1.** Add the JitPack repository to your `build.gradle[.kts]`:

<details open>

<summary>Kotlin DSL</summary>

```kotlin
repositories {
    // Other repositories
    maven(url = "https://jitpack.io") {
        content {
            includeGroup("com.github.ioki-mobility")
        }
    }
}
```

</details>

<details>

<summary>Groovy DSL</summary>

```groovy
repositories {
    // Other repositories
    maven {
        url 'https://jitpack.io'
        content {
            includeGroup('com.github.ioki-mobility"')
        }
    }
}
```

</details>

**Step 2.** Add the dependency:

<details open>

<summary>Kotlin DSL</summary>

```kotlin
dependencies {
    implementation("com.github.ioki-mobility:TextRef:<latest-version>")
}
```

</details>

<details>

<summary>Groovy DSL</summary>

```groovy
dependencies {
    implementation 'com.github.ioki-mobility:TextRef:<latest-version>'
}
```

</details>

## Releasing

**Step 1.** Make sure you are on the `main` branch.

**Step 2.** Add the changes to the top of [CHANGELOG.md](CHANGELOG.md)

**Step 3.** Commit and push

**Step 4.** Create a git tag with the version of the CHANGELOG and push

```
git tag x.y.z
git push origin x.y.z
```
