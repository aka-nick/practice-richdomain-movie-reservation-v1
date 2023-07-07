package org.akanick.moviereservation.domain;

// Rule의 도메인 모델 패턴 구현화로 인해 인터페이스로 변경, 이름이 겹치게 되어 'New'를 덧붙임
public interface NewRule {

    boolean isSatisfiedBy(Showing showing);

}
