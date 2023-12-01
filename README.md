# TextRef

[![CI](https://github.com/ioki-mobility/TextRef/actions/workflows/test-lib.yml/badge.svg)](https://github.com/ioki-mobility/TextRef/actions/workflows/test-lib.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.ioki.textref/textref?labelColor=%2324292E&color=%233246c8)](https://central.sonatype.com/namespace/com.ioki.textref)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg?labelColor=%2324292E&color=%23d11064)](https://github.com/ioki-mobility/TextRef/blob/master/LICENSE.md)

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

* API Level >= 21

## Jetpack Compose support

Next to the core implementation we also support Jetpack compose:

```kotlin
@Composable
fun RenderText(textRef: TextRef, nullableTextRef: TextRef?) {
    Text(text = textRef(textRef))
    textRefOrNull(nullableTextRef)?.let { Text(text = it) }
}
```

## Download

TextRef is hosted on Maven Central. Here's how you include it in your gradle project:

**Step 1.** Add the Maven Central repository to your `build.gradle[.kts]`:

```kotlin
repositories {
    mavenCentral()
}
```

**Step 2.** Add the dependency:

```kotlin
dependencies {
    implementation("com.ioki.textref:textref:<latest-version>")
}
```

**Step 2.1.** Add the optional **Jetpack compose** dependency:

```kotlin
dependencies {
    implementation("com.ioki.textref:compose:<latest-version>")
}
```

## Releasing

**Step 1.** Make sure you are on the `main` branch.

**Step 2.** Add the changes to the top of [CHANGELOG.md](CHANGELOG.md)

**Step 3.** Update the version in [`build.gradle.kts`](build.gradle.kts)

**Step 4.** Commit

```bash
git commit -m "Prepare next release" .
```

**Step 5.** Create a git tag with the version of the CHANGELOG and push the tag

```
git tag x.y.z
git push origin x.y.z
```

**Step 6.** Update the version [`build.gradle.kts`](build.gradle.kts) to the **next minor version** +`-SNAPSHOT`

**Step 7.** Commit and push the `main` branch

```bash
git push origin main
```
