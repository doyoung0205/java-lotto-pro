package study.lotto.model;

import java.util.HashSet;
import java.util.Set;

public class Lottery {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String ILLEGAL_LOTTERY_SIZE_ERROR_MESSAGE =
            "로또는 6개의 서로 다른 로또번호를 가지고 있어야 합니다.";

    private final Set<LottoNumber> lottoNumbers = new HashSet<>();

    protected Lottery(final Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    public static Lottery valueOf(final HashSet<LottoNumber> lottoNumbers) {
        return new Lottery(lottoNumbers);
    }

    public boolean containsAll(final HashSet<LottoNumber> lottoNumbers) {
        return this.lottoNumbers.containsAll(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lottery that = (Lottery) o;

        return lottoNumbers != null ? lottoNumbers.equals(that.lottoNumbers) : that.lottoNumbers == null;
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }

    private void validateSize(final Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalLotterySizeException(ILLEGAL_LOTTERY_SIZE_ERROR_MESSAGE);
        }
    }

}