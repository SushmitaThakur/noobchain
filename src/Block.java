import java.util.Date;

public class Block {
    
    public String hash;
    public String previouHash;
    private String data; // simple message
    private long timeStamp; // as number of miliseconds since 1/1/1970
    private int nonce = 0;

    // Block Constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previouHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String cacultedHash = StringUtil.applySha256(previouHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);

        return cacultedHash;
    }

    // Mining blocks
    public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
