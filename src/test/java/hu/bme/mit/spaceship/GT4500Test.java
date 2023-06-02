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

 @Test
 public void fireTorpedo_Single_Alternating_Success(){
    //Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);

    //Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    
    //Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
 } 

@Test
 public void fireTorpedo_FirstEmpty_Success(){
    //Arrange
    when(mock1.fire(1)).thenReturn(false);
    when(mock1.isEmpty()).thenReturn(true);
    when(mock1.getTorpedoCount()).thenReturn(0);
    when(mock2.fire(1)).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock2.getTorpedoCount()).thenReturn(1);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(true, result);
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(1)).fire(1);
 }

 @Test
 public void fireTorpedo_SecondEmpty_Success(){
    //Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock1.isEmpty()).thenReturn(false);
    when(mock1.getTorpedoCount()).thenReturn(1);
    when(mock2.fire(1)).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(true);
    when(mock2.getTorpedoCount()).thenReturn(0);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(true, result);
    verify(mock1, times(2)).fire(1);
    verify(mock2, times(0)).fire(1);
 }

  @Test
  public void fireTorpedo_All_firstEmpty(){
    //Arrange
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(true, result);
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
  }  

  @Test
  public void fireTorpedo_All_secondEmpty(){
    //Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock1.isEmpty()).thenReturn(false);
    when(mock1.getTorpedoCount()).thenReturn(1);
    when(mock2.fire(1)).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(true);
    when(mock2.getTorpedoCount()).thenReturn(0);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(false, result);
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(0)).fire(1);
  }  

}
