# Changelog

## 2.1.0

**Maintenance release**
* Update a lot of dependencies
* Add Gradle Wrapper Upgrade plugin
* Update Gradle Wrapper

Diff: [`2.0.0...2.1.0`](https://github.com/ioki-mobility/TextRef/compare/2.0.0...2.1.0)

## 2.0.0

* Jetpack Compose support
* **New maven coordinates**
Since we support Jetpack Compose, we updated our maven coordinates.
```
- com.ioki.ioki-mobility:TextRef:$version
+ com.ioki.ioki-mobility.TextRef:core:$version
+ com.ioki.ioki-mobility.TextRef:compose:$version // Optional
```

* Updates to dependencies
* minSdk bump to 21 (from 14)

Diff: [`1.3.1...2.0.0`](https://github.com/ioki-mobility/TextRef/compare/1.3.1...2.0.0)

## 1.3.1

* Fixes a bug regarding parameter arity
* Updates to dependencies

## 1.3.0

* Introduced factory functions and deprecated fake constructors
* Added Support for string plurals

## 1.2.0

Another TextRef can now be passed as a format arg (`TextRef("foo%s", TextRef("bar"))`)

## 1.1.0

TextRef now implements Parcelable

## 1.0.0

Initial release
