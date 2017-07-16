package com.king.common.persistence.search;

/**
 * Represents an strategy for matching strings using "like".
 *
 * @author by yjh
 * @DateTime 2017/7/15 21:33
 */
public enum MatchMode {

    /**
     * Match the start of the string to the pattern
     */
    LEFT {
        @Override
        public CriterionEnum toMatchCriterionEnum() {
            return CriterionEnum.LIKE_LEFT;
        }
    },

    /**
     * Match the end of the string to the pattern
     */
    RIGHT {
        @Override
        public CriterionEnum toMatchCriterionEnum() {
            return CriterionEnum.LIKE_RIGHT;
        }
    },

    /**
     * Match the pattern anywhere in the string
     */
    ALL {
        @Override
        public CriterionEnum toMatchCriterionEnum() {
            return CriterionEnum.LIKE_ALL;
        }
    };

    /**
     * Convert the pattern, by appending/prepending "%"
     *
     * @return The converted pattern
     */
    public abstract CriterionEnum toMatchCriterionEnum();

}
