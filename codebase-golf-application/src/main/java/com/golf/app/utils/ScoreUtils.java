package com.golf.app.utils;

import com.golf.app.dto.RoundScoreDto;
import com.golf.app.model.Hole;
import com.golf.app.model.RoundScore;
import com.golf.app.model.Score;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ScoreUtils {

    private static final int HOLES_QUANTITY = 18;

    public ScoreUtils() {
    }

    public static RoundScore roundScoreCalculator(RoundScore roundScore) {
        List<Score> scores = roundScore.getScores();
        Integer playerHcp = Math.toIntExact(Math.round(roundScore.getPlayer().getHcp()));

        int sumStroke = 0;
        int sumPenalty = 0;
        int sumPar = 0;
        int sumGrossScore = 0;
        int sumNetScore = 0;
        int sumNetScorePar = 0;
        int sumScoreSubtractPar = 0;
        int sumScoreSubtractParHcp = 0;
        int sumStableford = 0;
        for (Score score : scores) {

            int strokeInt = score.getStroke();
            Hole hole = roundScore.getCompetition()
                    .getCourse().getHoles().get(scores.indexOf(score));

            score.setStroke(strokeInt);
            sumStroke = sumStroke + strokeInt;
            roundScore.setSumStroke(sumStroke);

            score.setPenalty(score.getPenalty());
            sumPenalty = sumPenalty + score.getPenalty();
            roundScore.setSumPenalty(sumPenalty);

            sumPar = sumPar + hole.getPar();
            roundScore.setSumPar(sumPar);

            score.setGrossScore(strokeInt + score.getPenalty());
            sumGrossScore = sumGrossScore + score.getGrossScore();
            roundScore.setSumGrossScore(sumGrossScore);

            score.setScoreSubtractPar(strokeInt - hole.getPar());
            sumScoreSubtractPar = sumScoreSubtractPar + score.getScoreSubtractPar();
            roundScore.setSumScoreSubtractPar(sumScoreSubtractPar);

            Integer strokeIndex = hole.getStrokeIndex();
            Integer strokeToSubtruct = playerHcp % roundScore.getCompetition().getCourse().getHoles().size() - strokeIndex;
            int par = hole.getPar();
            if (strokeToSubtruct >= 0) {

                int allHolesCorrection = playerHcp / scores.size();
                strokeToSubtruct = allHolesCorrection + 1;

                score.setNetScore(score.getGrossScore() - strokeToSubtruct);
                score.setNetScorePar(score.getGrossScore() - strokeToSubtruct - par);

                score.setScoreSubtractParHcp(strokeInt - par - strokeToSubtruct);
            } else {
                int allHolesCorrection = playerHcp / scores.size();
                strokeToSubtruct = allHolesCorrection;

                score.setNetScore(score.getGrossScore() - strokeToSubtruct);
                score.setNetScorePar(score.getGrossScore() - strokeToSubtruct - par);

                score.setScoreSubtractParHcp(strokeInt - par - strokeToSubtruct);
            }

            sumNetScore = sumNetScore + score.getNetScore();
            roundScore.setSumNetScore(sumNetScore);

            sumNetScorePar = sumNetScorePar + score.getNetScorePar();
            roundScore.setSumNetScorePar(sumNetScorePar);

            int netScorePar = score.getNetScorePar();
            int scoreSubtractParHcp = score.getScoreSubtractParHcp();

            sumScoreSubtractParHcp = sumScoreSubtractParHcp + scoreSubtractParHcp;
            roundScore.setSumScoreSubtractParHcp(sumScoreSubtractParHcp);

            if (netScorePar == 1) {
                score.setStableford(1);
            } else if (netScorePar == 0) {
                score.setStableford(2);
            } else if (netScorePar == -1) {
                score.setStableford(3);
            } else if (netScorePar == -2) {
                score.setStableford(4);
            } else if (netScorePar == -3) {
                score.setStableford(5);
            }
            score.setStableford(0);

            sumStableford = sumStableford + score.getStableford();
            roundScore.setSumStableford(sumStableford);
        }
        return roundScore;
    }

    public static List<Score> buildScores(RoundScoreDto roundScoreDto) {
        return IntStream.range(0, HOLES_QUANTITY)
                .mapToObj(holeIndex -> {
                    Score score = new Score();
                    score.setStroke(roundScoreDto.getStrokes().get(holeIndex));
                    score.setPenalty(roundScoreDto.getPenalties().get(holeIndex));
                    return score;
                })
                .collect(Collectors.toList());
    }
}
