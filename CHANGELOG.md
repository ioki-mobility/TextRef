# Changelog

## 3.1.0

* New maven repository for snapshot versions
* Remove deprecated constructors (see https://github.com/ioki-mobility/TextRef/pull/164)
* A lot of dependency updates

Diff: [`3.0.0...3.1.0`](https://github.com/ioki-mobility/TextRef/compare/3.0.0...3.1.0)

## 3.0.0

**New maven coordinates**
Since we are publishing to Maven Central, we updated our maven coordinates.
```diff
-  com.ioki.ioki-mobility.TextRef:core:$version
+  com.ioki.textref:textref:$version
-  com.ioki.ioki-mobility.TextRef:compose:$version // Optional
+  com.ioki.textref:compose:$version // Optional
```

You also might want to adjust your repository declaration:
```diff
repositories {
-  maven(url = "https://jitpack.io")
+  mavenCentral()
}
```

* Bump kotlin dependency to 1.9.20
* Bump AGP dependency to 8.1.4
* Bump mockito dependency to 5.7.0
* Bump compose dependency to 1.5.4

Diff: [`2.2.2...3.0.0`](https://github.com/ioki-mobility/TextRef/compare/2.2.2...3.0.0)

## 2.2.2

* Use SPDX license URL for license in the POM file
* Bump compose dependencies to 1.5.3
* Bump mockito dependency to 5.6.0

Diff: [`2.2.1...2.2.2`](https://github.com/ioki-mobility/TextRef/compare/2.2.1...2.2.2)

## 2.2.1

* Use SPDX license identifier for license in the POM file

Diff: [`2.2.0...2.2.1`](https://github.com/ioki-mobility/TextRef/compare/2.2.0...2.2.1)

## 2.2.0

* Add license to POM file
* Upgrade Jetpack Compose (1.5.2)
* Upgrade Android Gradle Plugin (8.1.2)

Diff: [`2.1.0...2.2.0`](https://github.com/ioki-mobility/TextRef/compare/2.1.0...2.2.0)

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
```diff
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
