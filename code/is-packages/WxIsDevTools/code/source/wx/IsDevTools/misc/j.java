package wx.IsDevTools.misc;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.util.ArrayList;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void document2LogTrace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(document2LogTrace)>> ---
		// @sigtype java 3.5
		// [i] record:0:required record
		// [o] field:0:required traceText
		IDataCursor id = pipeline.getCursor();
		IData rec = IDataUtil.getIData( id, "record" );
		id.destroy();
		
		String traceText = null;
		if ( null != rec ){ 
			java.io.StringWriter s = new java.io.StringWriter();
			writeLogTrace("record", rec, 0, s, true);
			traceText = s.toString();
		}
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "traceText", traceText);
		pipelineCursor.destroy();			
		// --- <<IS-END>> ---

                
	}



	public static final void pipelineToLogTrace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(pipelineToLogTrace)>> ---
		// @sigtype java 3.5
		// [o] field:0:required traceText
		// see the Shared tab for dumpIData that walks the IData tree
		java.io.StringWriter s = new java.io.StringWriter();
		writeLogTrace("pipeline", pipeline, 0, s, true);
		final String traceText = s.toString();
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "traceText", traceText);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static final void writeKeyPart(String sType, String sKey, int indent, java.io.StringWriter out, boolean bAddl){
		out.write("\n");
		if(bAddl)
			out.write(INDENT_SPACE);
		for(int t=0;t<indent;t++)
			out.write(INDENT_SPACE);
		out.write(sType);
		out.write(" ");
		out.write(sKey);
	}
	
	private static final void writeLogTrace(String myKey, IData in, int indent, java.io.StringWriter out, boolean bRoot) throws ServiceException
	{
		IDataCursor idc = in.getCursor();
		writeKeyPart("{IData}", myKey, indent, out, false);
		while ( idc.next() )
		{
			String key = (String) idc.getKey();
			Object val = idc.getValue();
			if (val instanceof com.wm.util.coder.IDataCodable)
			{
				val = ((com.wm.util.coder.IDataCodable)val).getIData();
			}
			if (val instanceof String[][])
			{
				writeKeyPart("{java.lang.String[][]}", key, indent, out, true);
				String[][] st = (String[][])val;
				for (int k=0; k<st.length; k++)
				{
					for (int j=0; j<st[0].length; j++)
					{
						if(key.equalsIgnoreCase("password"))
							st[k][j]="*";
						out.write("\n");
						out.write(INDENT_SPACE);
						out.write(INDENT_SPACE);
						for(int t=0;t<indent;t++)
							out.write(INDENT_SPACE);
						out.write("["+k+"]["+j+"] = " + st[k][j]);
					}
				}
			}
			else if (val instanceof String[])
			{
				writeKeyPart("{java.lang.String[]}", key, indent, out, true);
	
				String[] sa = (String[])val;
				for (int k=0; k<sa.length; k++)
				{
					if(key.equalsIgnoreCase("password"))
						sa[k]="*";				
					out.write("\n");
					out.write(INDENT_SPACE);
					out.write(INDENT_SPACE);
					for(int t=0;t<indent;t++)
						out.write(INDENT_SPACE);
					out.write("["+k+"] = " + sa[k]);
				}
			}
			else if (val instanceof IData[])
			{
				writeKeyPart("{IData[]}", key, indent, out, true);
	
				IData[] ida = (IData[])val;
				for (int l=0; l<ida.length; l++)
				{
					writeLogTrace("[" + l + "]", ida[l], indent+2, out, false);
				}
			}
			else if (val instanceof IData)
			{
				writeLogTrace(key, (IData)val, indent+1, out, false);
			}
			else if (val instanceof com.wm.util.coder.IDataCodable[])
			{
				com.wm.util.coder.IDataCodable[] ida = (com.wm.util.coder.IDataCodable[])val;
				for (int l=0; l<ida.length; l++)
				{
					writeLogTrace(key, ida[l].getIData(), indent+1, out, false);
				}
			}
			else if(null == val){
				out.write("\n");
				out.write(INDENT_SPACE);
				for(int t=0;t<indent;t++)
					out.write(INDENT_SPACE);
				out.write("(null) " + key);
			}
			else if (val instanceof byte[]){
				writeKeyPart("{byte[]}", key, indent, out, true);
				out.write(" = *");
			}
			else if (val.getClass().isArray()){
				writeKeyPart("{java.lang.Object[]}", key, indent, out, true);
	
				Object[] oa = (Object[]) val;
				for (int k = 0; k < oa.length; k++) {
					out.write("\n");
					out.write(INDENT_SPACE);
					out.write(INDENT_SPACE);
					for(int t=0;t<indent;t++)
						out.write(INDENT_SPACE);
					if (key.equalsIgnoreCase("password"))
						oa[k] = "*";
					if(null == oa[k]){
						out.write("(null)[" + k +"]");
					}else{
						out.write("{" + oa[k].getClass().getCanonicalName()
								+ "}[" + k + "] = "
								+ oa[k].toString());
					}
				}
			}
			else
			{
				out.write("\n");
				out.write(INDENT_SPACE);
				for(int t=0;t<indent;t++)
					out.write(INDENT_SPACE);
				if(key.equalsIgnoreCase("password"))
					val="*";
				out.write("{"+val.getClass().getName()+"} " + key + " = " + val);
			}
		}
		idc.destroy();
	}
	
	private static final String INDENT_SPACE = "+ ";
	
		
	// --- <<IS-END-SHARED>> ---
}

