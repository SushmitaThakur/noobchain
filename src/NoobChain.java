import java.util.ArrayList;
import com.google.gson.GsonBuilder;

class NoobChain{

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 2;

    public static void main(String[] args){
        // Block genesisBlock = new Block("Hi, I am the first block.", "0");
        // System.out.println("Hash for block 1: " + genesisBlock.hash);

        // Block secondBlock = new Block( genesisBlock.hash);
        // System.out.println("Hash for block 2: " + secondBlock.hash);

        // Block thirdBlock = new Block("Hey, I am the third block", secondBlock.hash);
        // System.out.println("Hash for block 3: " + thirdBlock.hash);
        
        blockchain.add(new Block("Hi, I am the first block.", "0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo, I am the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);
       
        blockchain.add(new Block("Hey, I am the third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("The blockchain: " + blockchainJson);

        System.out.println("\nBlockchain is Valid: " + isChainValid());
    }

    // Validate the blockchain
    public static Boolean isChainValid(){

        Block currentBlock, previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++){

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current block does match the calculated hash.");
                return false;
            }

            if (!previousBlock.hash.equals(previousBlock.calculateHash())){
                System.out.println("Previouse block does match the stored hash.");
                return false;
            }

            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("The block has not been mined.");
                return false;
            }
        }

        return true;
    }
}
