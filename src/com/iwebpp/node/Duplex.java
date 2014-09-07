package com.iwebpp.node;

public abstract class Duplex extends Readable2 
implements Writable {
	private final static String TAG = "Duplex";

	private Writable2 _writable;
	protected Writable2.State _writableState;
	
	private class DuplexWritable extends Writable2 {
		private Duplex hold;
		private NodeContext _ctx;

		protected DuplexWritable(NodeContext ctx, Options options, Duplex hold) {
			super(ctx, options);
			this._ctx = ctx;
			// TODO Auto-generated constructor stub
			this.hold = hold;
		}
		private DuplexWritable() {super(null, null);}

		@Override
		public void _write(Object chunk, String encoding, WriteCB cb) throws Exception {
			// TODO Auto-generated method stub
			hold._write(chunk, encoding, cb);
		}
		
	}
	
	protected Duplex(NodeContext ctx, Readable2.Options roptions, Writable2.Options woptions) {
		super(ctx, roptions);
		// TODO Auto-generated constructor stub
		_writable = new DuplexWritable(ctx, woptions, this);
		_writableState = _writable._writableState;
	}

	private Duplex(){
		super(null, null);
	}
	
	@Override
	public boolean write(Object chunk, String encoding, WriteCB cb) throws Exception {
		// TODO Auto-generated method stub
		return _writable.write(chunk, encoding, cb);
	}
	@Override
	public void end(Object chunk, String encoding, WriteCB cb) throws Exception {
		// TODO Auto-generated method stub
		_writable.end(chunk, encoding, cb);
	}
	@Override
	public boolean writable() {
		// TODO Auto-generated method stub
		return _writable.writable();
	}
	
	@Override
	public abstract void _read(int size) throws Exception;
	
	public abstract void _write(Object chunk, String encoding, WriteCB cb) throws Exception;
	
}