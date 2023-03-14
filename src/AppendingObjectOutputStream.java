import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendingObjectOutputStream extends ObjectOutputStream {
	
	private boolean append;
	private boolean initialised;
	private DataOutputStream dout;
	
	protected AppendingObjectOutputStream (boolean append) throws IOException, SecurityException{
		super();
		this.append = append;
		this.initialised = true;
	}
	
	public AppendingObjectOutputStream (OutputStream out, boolean append) throws IOException{
		super(out);
		this.append = append;
		this.initialised = true;
		this.dout = new DataOutputStream(out);
		this.writeStreamHeader();
	}
	
	@Override
	protected void writeStreamHeader() throws IOException{
		if(!this.initialised || this.append) return;
		if(dout != null) {
			dout.writeShort(STREAM_MAGIC);
			dout.writeShort(STREAM_VERSION);
		}
	}
	
}