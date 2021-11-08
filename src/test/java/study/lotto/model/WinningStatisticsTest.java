package study.lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningStatisticsTest {
    TicketLotteryBundle ticketLotteryBundle;
    WinningLottery winningLottery;

    @BeforeEach
    void setUp() {

        final List<TicketLottery> ticketLotteries = Arrays.asList(
                TicketLottery.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6), TicketLotteryType.AUTO),
                TicketLottery.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6), TicketLotteryType.AUTO),
                TicketLottery.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6), TicketLotteryType.AUTO),
                TicketLottery.valueOf(Arrays.asList(1, 2, 3, 4, 5, 9), TicketLotteryType.AUTO)
        );

        ticketLotteryBundle = TicketLotteryBundle.valueOf(ticketLotteries);
        winningLottery = WinningLottery.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6), LottoNumber.valueOf(9));
    }

    @Test
    void 로또복권묶음과_당첨번호를_비교하여_수익률을_알_수_있다() {
        final WinningStatistics winningStatistics = WinningStatistics.valueOf(ticketLotteryBundle, winningLottery);
        final double incomeRate = winningStatistics.getIncomeRate();
        assertEquals(((2_000_000_000d * 3) + 30_000_000) / 4000, incomeRate);
    }

    @Test
    void 로또복권묶음과_당첨번호를_비교하여_당첨등수별_개수를_확인_할_수_있다() {
        final WinningStatistics winningStatistics = WinningStatistics.valueOf(ticketLotteryBundle, winningLottery);
        assertAll(() -> {
            assertThat(winningStatistics.countByRank(Rank.FIRST)).isEqualTo(3);
            assertThat(winningStatistics.countByRank(Rank.SECOND)).isEqualTo(1);
            assertThat(winningStatistics.countByRank(Rank.THIRD)).isEqualTo(0);
            assertThat(winningStatistics.countByRank(Rank.FOURTH)).isEqualTo(0);
            assertThat(winningStatistics.countByRank(Rank.FIFTH)).isEqualTo(0);
            assertThat(winningStatistics.countByRank(Rank.MISS)).isEqualTo(0);
        });
    }
}