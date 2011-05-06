package com.cd.chat.specification;

public interface ISpecification<T>
{
    boolean isSatisfiedBy(T sut);
}
