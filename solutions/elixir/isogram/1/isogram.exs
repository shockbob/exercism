defmodule Isogram do
  @doc """
  Determines if a word or sentence is an isogram
  """
  @spec isogram?(String.t()) :: boolean
  def isogram?(sentence) do
      
      characters = 
      sentence
      |> String.downcase()
      |> String.replace(~r([^a-z]),"")
      |> String.to_charlist();
 
      Enum.uniq(characters) == characters
  end
end
