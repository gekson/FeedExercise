package algorithmsandconstructs;

public final class FeedItem {

	
	public final String title;
	public final String content;
	  
	public FeedItem(String title, String content) {
		this.title = title;
	    this.content = content;
	}
	  
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if(this == obj) {
	    	return true;
	    }
	    if(!(obj instanceof FeedItem)) {
	    	return false;
	    }
	    else {
	    	FeedItem item = (FeedItem) obj;
		    
		    return (title == null? item.title == null : title.equals(item.title)) &&
		        (content == null? item.content == null : content.equals(item.content));
	    }
	    
	}
	  
	@Override
	public int hashCode() {
	    int result = (title == null? 0 : title.hashCode());
	    return 31 * result + (content == null? 0 : content.hashCode());
	}
}
