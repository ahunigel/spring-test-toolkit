package com.github.ahunigel.test.assertj;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.assertj.core.internal.TypeComparators;
import org.assertj.core.util.VisibleForTesting;
import org.assertj.core.util.introspection.FieldSupport;
import org.assertj.core.util.introspection.PropertyOrFieldSupport;
import org.assertj.core.util.introspection.PropertySupport;

import java.lang.reflect.Field;
import java.util.*;

import static org.assertj.core.error.ShouldBeEqualToIgnoringFields.shouldBeEqualToIgnoringGivenFields;
import static org.assertj.core.internal.Objects.getDeclaredFieldsIncludingInherited;
import static org.assertj.core.internal.TypeComparators.defaultTypeComparators;
import static org.assertj.core.util.Sets.newLinkedHashSet;

/**
 * Enhanced object assert for <code>Assertj</code>
 * Created by nigel on 2020/3/15.
 *
 * @author nigel
 * @todo find a way to expose the class
 * @see org.assertj.core.api.ObjectAssert
 */
public abstract class EnhancedObjectAssert<SELF extends EnhancedObjectAssert<SELF, ACTUAL>, ACTUAL> extends AbstractAssert<SELF, ACTUAL> {
  public EnhancedObjectAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  @VisibleForTesting
  Objects objects = Objects.instance();
  @VisibleForTesting
  Failures failures = Failures.instance();
  @VisibleForTesting
  final PropertySupport propertySupport = PropertySupport.instance();
  @VisibleForTesting
  private final FieldSupport fieldSupport = FieldSupport.comparison();

  private Map<String, Comparator<?>> comparatorByPropertyOrField = new TreeMap<>();
  private TypeComparators comparatorByType = defaultTypeComparators();

  public SELF isEqualToIgnoringNullFieldsAndGivenFields(Object other, String... propertiesOrFieldsToIgnore) {
    objects.assertNotNull(info, actual);
    List<String> fieldsNames = new LinkedList<>();
    List<Object> rejectedValues = new LinkedList<>();
    List<Object> expectedValues = new LinkedList<>();
    List<String> nullFields = new LinkedList<>();
    for (Field field : getDeclaredFieldsIncludingInherited(actual.getClass())) {
      if (!canReadFieldValue(field, actual)) continue;
      String fieldName = field.getName();
      Object otherFieldValue = getPropertyOrFieldValue(other, fieldName);
      if (otherFieldValue == null) {
        nullFields.add(fieldName);
      } else {
        Object actualFieldValue = getPropertyOrFieldValue(actual, fieldName);
        if (!propertyOrFieldValuesAreEqual(actualFieldValue, otherFieldValue, fieldName,
            comparatorByPropertyOrField, comparatorByType)) {
          fieldsNames.add(fieldName);
          rejectedValues.add(actualFieldValue);
          expectedValues.add(otherFieldValue);
        }
      }
    }
    Set<String> ignoredFields = newLinkedHashSet(propertiesOrFieldsToIgnore);
    ignoredFields.addAll(nullFields);

    if (!fieldsNames.isEmpty())
      throw failures.failure(info, shouldBeEqualToIgnoringGivenFields(actual, fieldsNames,
          rejectedValues, expectedValues, new LinkedList<>(ignoredFields)));

    return myself;
  }

  //------------------------------------------------------------
  private <A> boolean canReadFieldValue(Field field, A actual) {
    return fieldSupport.isAllowedToRead(field) || propertySupport.publicGetterExistsFor(field.getName(), actual);
  }

  private <A> Object getPropertyOrFieldValue(A a, String fieldName) {
    return PropertyOrFieldSupport.COMPARISON.getValueOf(fieldName, a);
  }

  static boolean propertyOrFieldValuesAreEqual(Object actualFieldValue, Object otherFieldValue, String fieldName,
                                               Map<String, Comparator<?>> comparatorByPropertyOrField,
                                               TypeComparators comparatorByType) {
    // no need to look into comparators if objects are the same
    if (actualFieldValue == otherFieldValue) return true;
    // check field comparators as they take precedence over type comparators
    Comparator fieldComparator = comparatorByPropertyOrField.get(fieldName);
    if (fieldComparator != null) return fieldComparator.compare(actualFieldValue, otherFieldValue) == 0;
    // check if a type comparators exist for the field type
    Class fieldType = actualFieldValue != null ? actualFieldValue.getClass() : otherFieldValue.getClass();
    Comparator typeComparator = comparatorByType.get(fieldType);
    if (typeComparator != null) return typeComparator.compare(actualFieldValue, otherFieldValue) == 0;
    // default comparison using equals
    return org.assertj.core.util.Objects.areEqual(actualFieldValue, otherFieldValue);
  }
}
