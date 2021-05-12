package com.fsega.animalbrokers.utils.collectionutils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@UtilityClass
public class CollectionUtils {

    public static <E, D> List<D> toSortedList(Collection<E> collection, Comparator<E> comparator, Function<E, D> function) {
        return ofNullable(collection)
                .orElseGet(ArrayList::new)
                .stream()
                .sorted(comparator)
                .map(function)
                .collect(Collectors.toList());
    }

}
