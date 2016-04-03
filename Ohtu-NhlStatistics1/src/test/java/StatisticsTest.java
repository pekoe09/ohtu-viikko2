
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void canFindPlayerByName(){
        Player returnedPlayer = stats.search("Kurri");
        assertEquals("Kurri", returnedPlayer.getName());
        assertEquals("EDM", returnedPlayer.getTeam());
        assertEquals(37, returnedPlayer.getGoals());
        assertEquals(53, returnedPlayer.getAssists());
    }
    
    @Test
    public void returnNullIfPlayerNameNotListed(){
        Player returnedPlayer = stats.search("Selänne");
        assertNull(returnedPlayer);
    }
    
    @Test
    public void canReturnTeamPlayersByTeamName(){
        String[] players = new String[]{"Kurri", "Semenko", "Gretzky"};
        List<Player> returnedTeam = stats.team("EDM");
        assertEquals(3, returnedTeam.size());
        int foundPlayers = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < returnedTeam.size(); j++){
                if(players[i].equals(returnedTeam.get(j).getName())){
                    foundPlayers++;
                }
            }
        }
        assertEquals(3, foundPlayers);
    }
    
    @Test
    public void canReturnTopNScorers(){
        String[] scorers = new String[]{"Lemieux", "Yserman", "Gretzky"};
        List<Player> returnedScorers = stats.topScorers(3);
        assertEquals(3, returnedScorers.size());
        int foundScorers = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < returnedScorers.size(); j++){
                if(scorers[i].equals(returnedScorers.get(j).getName())){
                    foundScorers++;
                }
            }
        }
        assertEquals(3, foundScorers);
    }
}
