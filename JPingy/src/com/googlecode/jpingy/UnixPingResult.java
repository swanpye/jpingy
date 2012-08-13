package com.googlecode.jpingy;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnixPingResult extends PingResult {

	private String[] pack;

	public UnixPingResult(List<String> pingOutput) {
		super(pingOutput);

	}

	private void generatePackageArray(List<String> lines) {
		if (pack == null) {
			String packages = lines.get(lines.size() - 2);
			pack = packages.split(",");
		}
	}

	public int matchTransmitted(List<String> lines) {
		generatePackageArray(lines);
		return Integer.parseInt(pack[0].replaceAll("\\D+", ""));

	}

	public int matchReceived(List<String> lines) {
		generatePackageArray(lines);
		return Integer.parseInt(pack[1].replaceAll("\\D+", ""));

	}

	public int matchTime(List<String> lines) {
		generatePackageArray(lines);
		return Integer.parseInt(pack[3].replaceAll("\\D+", ""));

	}

	private void generateRttArray(List<String> lines) {
		if (rtt == null) {
			// rtt
			String rtts = lines.get(lines.size() - 1);
			String[] rtt_equals = rtts.split("=");
			rtt = rtt_equals[1].split("/");
		}
	}

	private String[] rtt;

	@Override
	public float matchRttMin(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[0]);

	}

	@Override
	public float matchRttAvg(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[1]);
	}

	@Override
	public float matchRttMax(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[2]);
	}

	@Override
	public float matchRttMdev(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[3].replaceAll("\\D+", ""));
	}

	@Override
	public String matchIP(List<String> lines) {
		String str = lines.toString();
		String pattern = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		m.find();

		return m.toMatchResult().group(0);
	}

	@Override
	public int matchTTL(List<String> lines) {
		String str = lines.toString();
		Pattern pattern = Pattern.compile("ttl=([0-9\\.]+)"); // match
		// ttl=decimal

		Matcher matcher = pattern.matcher(str.toString());

		matcher.find();
		MatchResult result = matcher.toMatchResult();

		return Integer.parseInt(result.group(1).replaceAll("ttl=", ""));
	}

	@Override
	public String toString() {
		return "PingResult [address=" + address + ", transmitted="
				+ transmitted + ", ttl=" + ttl + ", time=" + time
				+ ", received=" + received + ", payload=" + payload
				+ ", rtt_min=" + rtt_min + ", rtt_avg=" + rtt_avg
				+ ", rtt_max=" + rtt_max + ", rtt_mdev=" + rtt_mdev + "]";
	}

	@Override
	protected int parsePayload(List<String> lines) {
		// TODO Auto-generated method stub

		return Integer.parseInt(lines.get(1).split("bytes")[0].trim());
	}

}