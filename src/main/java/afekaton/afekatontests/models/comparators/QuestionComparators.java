package afekaton.afekatontests.models.comparators;

import afekaton.afekatontests.models.questions.Question;

import java.util.Comparator;

/**
 * Created by SBK on 3/19/2018.
 */
public class QuestionComparators {
    public static final Comparator<Question> sortByCreationDate = new Comparator<Question>() {
        @Override
        public int compare(Question o1, Question o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    };
}
