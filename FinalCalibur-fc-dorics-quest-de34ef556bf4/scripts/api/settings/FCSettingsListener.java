package scripts.api.settings;

public interface FCSettingsListener
{
	public void settingChanged(int index, int oldValue, int newValue);
}
