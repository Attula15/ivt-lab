package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mock1;
  private TorpedoStore mock2;

  @BeforeEach
  public void init(){
    mock1 = mock(TorpedoStore.class);
    mock2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mock1, mock2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    

    // Assert
    assertEquals(true, result);
    verify(mock1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
  }

}
