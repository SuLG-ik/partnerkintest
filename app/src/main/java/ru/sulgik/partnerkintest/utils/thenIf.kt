package ru.sulgik.partnerkintest.utils
import androidx.compose.ui.Modifier


inline fun Modifier.thenIf(condition: Boolean, lazyProduce: () -> Modifier): Modifier {
    return this.then(if (condition) lazyProduce() else Modifier)
}

inline fun <T> Modifier.thenIfNotNull(value: T?, consume: (T) -> Modifier): Modifier {
    return this.thenIf(value != null) { consume(value!!) }
}

fun Modifier.thenUnless(condition: Boolean, other: Modifier): Modifier {
    return this.thenUnless(condition) { other }
}

inline fun Modifier.thenUnless(condition: Boolean, lazyProduce: () -> Modifier): Modifier {
    return this.thenIf(!condition, lazyProduce)
}
