package study.lotto.model;

import study.lotto.model.exception.DuplicatedBonusLottoNumberException;
import study.lotto.model.exception.EmptyBonusLottoNumberException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLottery {

    private static final String DUPLICATED_ERROR_MESSAGE = "보너스 번호는 기존에 당첨번호와 중복될 수 없습니다.";
    public static final String NOT_NULL_ERROR_MESSAGE = "보너스 번호는 필수 입니다.";

    private final Lottery lottery;
    private final LottoNumber bonusLottoNumber;

    private WinningLottery(final Set<Integer> lottoNumbers, final LottoNumber bonusLottoNumber) {
        this.lottery = Lottery.valueOf(lottoNumbers);
        validateBonusNumber(bonusLottoNumber);
        this.bonusLottoNumber = bonusLottoNumber;
    }

    public static WinningLottery valueOf(final Set<Integer> lottoNumbers, final LottoNumber bonusLottoNumber) {
        return new WinningLottery(lottoNumbers, bonusLottoNumber);
    }

    public static WinningLottery valueOf(final List<Integer> lottoNumbers, final LottoNumber bonusLottoNumber) {
        return new WinningLottery(new HashSet<>(lottoNumbers), bonusLottoNumber);
    }

    private void validateBonusNumber(LottoNumber bonusLottoNumber) {
        validateNotNullBonusLottoNumber(bonusLottoNumber);
        validateDuplicateBonusLottoNumber(bonusLottoNumber);
    }

    private void validateNotNullBonusLottoNumber(final LottoNumber bonusLottoNumber) {
        if (bonusLottoNumber == null) {
            throw new EmptyBonusLottoNumberException(NOT_NULL_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateBonusLottoNumber(final LottoNumber bonusLottoNumber) {
        if (lottery.getLottoNumbers().contains(bonusLottoNumber)) {
            throw new DuplicatedBonusLottoNumberException(DUPLICATED_ERROR_MESSAGE);
        }
    }

    public boolean isMatchBonusNumber(final TicketLottery ticketLottery) {
        return ticketLottery.contains(bonusLottoNumber);
    }


    public int match(final TicketLottery ticketLottery) {
        final Set<LottoNumber> ticketLotteryLottoNumbers = ticketLottery.getLottoNumbers();
        int matchCount = 0;
        for (final LottoNumber ticketLotteryLottoNumber : ticketLotteryLottoNumbers) {
            matchCount = plusIfContains(ticketLotteryLottoNumber, matchCount);
        }
        return matchCount;
    }

    private int plusIfContains(final LottoNumber lottoNumber, int matchCount) {
        if (lottery.contains(lottoNumber)) {
            matchCount++;
        }
        return matchCount;
    }

}
