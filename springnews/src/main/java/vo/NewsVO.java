package vo;

public class NewsVO {
	
	private int newsId;
	private String writer;
	private String title;
	private String content;
	private String writedate;
	private int cnt;

	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt += cnt;
	}
	
	@Override
	public String toString() {
		return "NewsVO [id=" + newsId + ", writer=" + writer + ", title=" + title + ", content=" + content + ", writedate="
				+ writedate + ", cnt=" + cnt + "]";
	}
	
}
