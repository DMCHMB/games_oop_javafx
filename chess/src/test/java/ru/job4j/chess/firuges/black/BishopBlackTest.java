package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class BishopBlackTest {

    @Test
    public void whenReturnCorrectPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell expected = Cell.C8;
        Cell actual = bishopBlack.position();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void whenCorrectCopy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell expected = Cell.E6;
        Figure copyBishopBlack = bishopBlack.copy(expected);
        assertThat(copyBishopBlack).isNotEqualTo(bishopBlack);
        assertThat(copyBishopBlack.position()).isEqualTo(expected);
    }

    @Test
    public void whenCorrectWayFromC1ToG5() {
        Cell source = Cell.C1;
        Cell destination = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(source);
        Cell[] expectedWay = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] actualWay = bishopBlack.way(destination);
        assertThat(actualWay).containsExactly(expectedWay);
    }

    @Test
    public void whenIncorrectWayFromC1ToG4() {
        Cell source = Cell.C1;
        Cell destination = Cell.G2;
        BishopBlack bishopBlack = new BishopBlack(source);
        ImpossibleMoveException impossibleMoveException = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(destination);
                }
        );
        assertThat(impossibleMoveException.getMessage())
                .isEqualTo("Could not move by diagonal from %s to %s", source, destination);
    }

    @Test
    public void whenWayIsDiagonal() {
        Cell source = Cell.E3;
        Cell destination = Cell.C1;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean actual = bishopBlack.isDiagonal(bishopBlack.position(), destination);
        assertThat(actual).isTrue();
    }

    @Test
    public void whenWayIsNotDiagonal() {
        Cell source = Cell.E3;
        Cell destination = Cell.E2;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean actual = bishopBlack.isDiagonal(bishopBlack.position(), destination);
        assertThat(actual).isFalse();
    }
}