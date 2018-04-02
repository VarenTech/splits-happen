/**
 * 
 */
package bowling;

/**
 * @author Jason
 *
 */
final class Frame {
	private String throw1, throw2;

	Frame(String throw1,String throw2){
		this.throw1 = throw1;
		this.throw2 = throw2;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.append("|\t").
				append(throw1).
				append(" | ").
				append(throw2.length() > 0 ? throw2: " ").
				append("\t\t").toString();
	}
}
